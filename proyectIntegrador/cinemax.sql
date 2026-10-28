CREATE DATABASE CineMaxCesde;
USE CineMaxCesde;
GO

-- ============================================================================
-- 2. TABLAS CATÁLOGO / ENUMS (3FN: Evitan dependencias transitivas y redundancia)
-- ============================================================================

-- 2.1 Catálogo de Géneros de Películas (Enum: GeneroPelicula)
CREATE TABLE dbo.generos_pelicula (
    id INT IDENTITY(1,1) NOT NULL,
    codigo VARCHAR(30) NOT NULL,
    descripcion NVARCHAR(50) NOT NULL,
    CONSTRAINT PK_generos_pelicula PRIMARY KEY CLUSTERED (id),
    CONSTRAINT UQ_generos_pelicula_codigo UNIQUE (codigo)
);
GO

-- 2.2 Catálogo de Tipos de Sala (Enum: TipoSala)
-- 3FN: El recargo depende directamente del tipo de sala, no de la sala individual.
CREATE TABLE dbo.tipos_sala (
    id INT IDENTITY(1,1) NOT NULL,
    codigo VARCHAR(20) NOT NULL,
    descripcion NVARCHAR(50) NOT NULL,
    recargo DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    CONSTRAINT PK_tipos_sala PRIMARY KEY CLUSTERED (id),
    CONSTRAINT UQ_tipos_sala_codigo UNIQUE (codigo),
    CONSTRAINT CK_tipos_sala_recargo CHECK (recargo >= 0)
);
GO

-- 2.3 Catálogo de Métodos de Pago (Enum: MetodoPago)
CREATE TABLE dbo.metodos_pago (
    id INT IDENTITY(1,1) NOT NULL,
    codigo VARCHAR(20) NOT NULL,
    nombre NVARCHAR(50) NOT NULL,
    CONSTRAINT PK_metodos_pago PRIMARY KEY CLUSTERED (id),
    CONSTRAINT UQ_metodos_pago_codigo UNIQUE (codigo)
);
GO

-- 2.4 Catálogo de Estados de Reserva (Enum: EstadoReserva)
CREATE TABLE dbo.estados_reserva (
    id INT IDENTITY(1,1) NOT NULL,
    codigo VARCHAR(20) NOT NULL,
    descripcion NVARCHAR(50) NOT NULL,
    CONSTRAINT PK_estados_reserva PRIMARY KEY CLUSTERED (id),
    CONSTRAINT UQ_estados_reserva_codigo UNIQUE (codigo)
);
GO

-- ============================================================================
-- 3. TABLAS DE DOMINIO PRINCIPAL (3FN)
-- ============================================================================

-- 3.1 Persona (Entidad base: Persona.java)
CREATE TABLE dbo.personas (
    id INT IDENTITY(1,1) NOT NULL,
    nombre NVARCHAR(100) NOT NULL,
    correo NVARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    fecha_registro DATETIME2(0) NOT NULL DEFAULT SYSDATETIME(),
    CONSTRAINT PK_personas PRIMARY KEY CLUSTERED (id),
    CONSTRAINT UQ_personas_correo UNIQUE (correo)
);
GO

-- 3.2 Cliente (Especialización: Cliente.java - Patrón Table-per-Type en 3FN)
-- Comparte la misma PK que personas(id) como FK (Relación 1:1 de herencia)
CREATE TABLE dbo.clientes (
    id INT NOT NULL,
    edad INT NOT NULL,
    CONSTRAINT PK_clientes PRIMARY KEY CLUSTERED (id),
    CONSTRAINT FK_clientes_personas FOREIGN KEY (id)
        REFERENCES dbo.personas (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT CK_clientes_edad CHECK (edad >= 0 AND edad <= 120)
);
GO

-- 3.3 Película (Pelicula.java)
CREATE TABLE dbo.peliculas (
    id INT IDENTITY(1,1) NOT NULL,
    titulo NVARCHAR(150) NOT NULL,
    genero_id INT NOT NULL,
    duracion_minutos INT NOT NULL,
    precio_base DECIMAL(10,2) NOT NULL,
    edad_minima INT NOT NULL DEFAULT 0,
    CONSTRAINT PK_peliculas PRIMARY KEY CLUSTERED (id),
    CONSTRAINT FK_peliculas_genero FOREIGN KEY (genero_id)
        REFERENCES dbo.generos_pelicula (id),
    CONSTRAINT CK_peliculas_duracion CHECK (duracion_minutos > 0),
    CONSTRAINT CK_peliculas_precio CHECK (precio_base >= 0),
    CONSTRAINT CK_peliculas_edad_minima CHECK (edad_minima >= 0)
);
GO

-- 3.4 Sala (Sala.java)
CREATE TABLE dbo.salas (
    id INT IDENTITY(1,1) NOT NULL,
    nombre NVARCHAR(50) NOT NULL,
    capacidad INT NOT NULL,
    tipo_sala_id INT NOT NULL,
    CONSTRAINT PK_salas PRIMARY KEY CLUSTERED (id),
    CONSTRAINT FK_salas_tipo_sala FOREIGN KEY (tipo_sala_id)
        REFERENCES dbo.tipos_sala (id),
    CONSTRAINT CK_salas_capacidad CHECK (capacidad > 0)
);
GO

-- 3.5 Función (Funcion.java)
-- Relaciona Película + Sala con Fecha y Hora de proyección
CREATE TABLE dbo.funciones (
    id INT IDENTITY(1,1) NOT NULL,
    pelicula_id INT NOT NULL,
    sala_id INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME(0) NOT NULL,
    CONSTRAINT PK_funciones PRIMARY KEY CLUSTERED (id),
    CONSTRAINT FK_funciones_pelicula FOREIGN KEY (pelicula_id)
        REFERENCES dbo.peliculas (id),
    CONSTRAINT FK_funciones_sala FOREIGN KEY (sala_id)
        REFERENCES dbo.salas (id),
    -- Regla de integridad: No programar la misma sala a la misma fecha y hora
    CONSTRAINT UQ_funciones_sala_fecha_hora UNIQUE (sala_id, fecha, hora)
);
GO

-- 3.6 Boleto (Boleto.java)
CREATE TABLE dbo.boletos (
    id INT IDENTITY(1,1) NOT NULL,
    codigo VARCHAR(30) NOT NULL,
    funcion_id INT NOT NULL,
    asiento VARCHAR(10) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    CONSTRAINT PK_boletos PRIMARY KEY CLUSTERED (id),
    CONSTRAINT UQ_boletos_codigo UNIQUE (codigo),
    CONSTRAINT FK_boletos_funcion FOREIGN KEY (funcion_id)
        REFERENCES dbo.funciones (id),
    -- Regla: Mismo asiento no puede venderse dos veces para la misma función
    CONSTRAINT UQ_boletos_funcion_asiento UNIQUE (funcion_id, asiento),
    CONSTRAINT CK_boletos_precio CHECK (precio >= 0)
);
GO

-- 3.7 Reserva (Reserva.java)
CREATE TABLE dbo.reservas (
    id INT IDENTITY(1,1) NOT NULL,
    cliente_id INT NOT NULL,
    funcion_id INT NOT NULL,
    cantidad_boletos INT NOT NULL,
    estado_id INT NOT NULL,
    fecha_reserva DATETIME2(0) NOT NULL DEFAULT SYSDATETIME(),
    CONSTRAINT PK_reservas PRIMARY KEY CLUSTERED (id),
    CONSTRAINT FK_reservas_cliente FOREIGN KEY (cliente_id)
        REFERENCES dbo.clientes (id),
    CONSTRAINT FK_reservas_funcion FOREIGN KEY (funcion_id)
        REFERENCES dbo.funciones (id),
    CONSTRAINT FK_reservas_estado FOREIGN KEY (estado_id)
        REFERENCES dbo.estados_reserva (id),
    CONSTRAINT CK_reservas_cantidad CHECK (cantidad_boletos > 0)
);
GO

-- 3.8 Factura (Factura.java)
CREATE TABLE dbo.facturas (
    id INT IDENTITY(1,1) NOT NULL,
    numero VARCHAR(30) NOT NULL,
    reserva_id INT NOT NULL,
    metodo_pago_id INT NOT NULL,
    pagada BIT NOT NULL DEFAULT 0,
    fecha_emision DATETIME2(0) NOT NULL DEFAULT SYSDATETIME(),
    CONSTRAINT PK_facturas PRIMARY KEY CLUSTERED (id),
    CONSTRAINT UQ_facturas_numero UNIQUE (numero),
    -- Relación 1:1 estricta: Cada reserva genera a lo sumo una factura
    CONSTRAINT UQ_facturas_reserva UNIQUE (reserva_id),
    CONSTRAINT FK_facturas_reserva FOREIGN KEY (reserva_id)
        REFERENCES dbo.reservas (id),
    CONSTRAINT FK_facturas_metodo_pago FOREIGN KEY (metodo_pago_id)
        REFERENCES dbo.metodos_pago (id)
);
GO

-- ============================================================================
-- 4. ÍNDICES PARA OPTIMIZACIÓN (Best Practices Azure SQL para FKs)
-- ============================================================================
CREATE NONCLUSTERED INDEX IX_peliculas_genero_id ON dbo.peliculas(genero_id);
CREATE NONCLUSTERED INDEX IX_salas_tipo_sala_id ON dbo.salas(tipo_sala_id);
CREATE NONCLUSTERED INDEX IX_funciones_pelicula_id ON dbo.funciones(pelicula_id);
CREATE NONCLUSTERED INDEX IX_funciones_sala_id ON dbo.funciones(sala_id);
CREATE NONCLUSTERED INDEX IX_boletos_funcion_id ON dbo.boletos(funcion_id);
CREATE NONCLUSTERED INDEX IX_reservas_cliente_id ON dbo.reservas(cliente_id);
CREATE NONCLUSTERED INDEX IX_reservas_funcion_id ON dbo.reservas(funcion_id);
CREATE NONCLUSTERED INDEX IX_reservas_estado_id ON dbo.reservas(estado_id);
CREATE NONCLUSTERED INDEX IX_facturas_metodo_pago_id ON dbo.facturas(metodo_pago_id);
GO

-- ============================================================================
-- 5. DATOS SEMILLA (SEEDS) - Mapeo de Enums de Java
-- ============================================================================

-- 5.1 Semillas de Géneros de Película
INSERT INTO dbo.generos_pelicula (codigo, descripcion) VALUES
('ACCION', N'Acción'),
('COMEDIA', N'Comedia'),
('DRAMA', N'Drama'),
('TERROR', N'Terror'),
('ANIMACION', N'Animación'),
('CIENCIA_FICCION', N'Ciencia Ficción');
GO

-- 5.2 Semillas de Tipos de Sala
INSERT INTO dbo.tipos_sala (codigo, descripcion, recargo) VALUES
('DOS_D', N'2D Tradicional', 0.00),
('TRES_D', N'3D', 3000.00),
('VIP', N'Sala VIP', 5000.00),
('IMAX', N'Sala IMAX', 8000.00);
GO

-- 5.3 Semillas de Métodos de Pago
INSERT INTO dbo.metodos_pago (codigo, nombre) VALUES
('EFECTIVO', N'Efectivo'),
('TARJETA', N'Tarjeta de Crédito / Débito'),
('NEQUI', N'Nequi'),
('DAVIPLATA', N'Daviplata');
GO

-- 5.4 Semillas de Estados de Reserva
INSERT INTO dbo.estados_reserva (codigo, descripcion) VALUES
('PENDIENTE', N'Pendiente'),
('CONFIRMADA', N'Confirmada'),
('CANCELADA', N'Cancelada');
GO

-- ============================================================================
-- 6. VISTAS DE NEGOCIO (3FN: Cálculos y agregaciones en tiempo de consulta)
-- ============================================================================

-- Vista para consultar clientes con sus datos de persona
CREATE OR ALTER VIEW dbo.vw_clientes_detalle AS
SELECT 
    c.id AS cliente_id,
    p.nombre,
    p.correo,
    p.telefono,
    c.edad,
    CASE WHEN c.edad >= 18 THEN 1 ELSE 0 END AS es_mayor_de_edad,
    p.fecha_registro
FROM dbo.clientes c
INNER JOIN dbo.personas p ON c.id = p.id;
GO

-- Vista para consultar funciones con precio base y recargo calculado
CREATE OR ALTER VIEW dbo.vw_funciones_cartelera AS
SELECT 
    f.id AS funcion_id,
    p.id AS p_id,
    p.titulo,
    gp.descripcion AS p_genero,
    p.duracion_minutos,
    p.precio_base,
    p.edad_minima,
    s.id AS s_id,
    s.nombre AS s_nombre,
    s.capacidad AS s_capacidad,
    ts.descripcion AS s_tipo,
    ts.recargo AS s_recargo,
    CONVERT(VARCHAR(10), f.fecha, 120) AS fecha,
    CONVERT(VARCHAR(8), f.hora, 108) AS hora,
    (p.precio_base + ts.recargo) AS precio_boleto
FROM dbo.funciones f
INNER JOIN dbo.peliculas p ON f.pelicula_id = p.id
INNER JOIN dbo.generos_pelicula gp ON p.genero_id = gp.id
INNER JOIN dbo.salas s ON f.sala_id = s.id
INNER JOIN dbo.tipos_sala ts ON s.tipo_sala_id = ts.id;
GO

-- Vista con el cálculo de subtotales, descuentos y totales de reservas
CREATE OR ALTER VIEW dbo.vw_reservas_detalle AS
SELECT 
    r.id AS reserva_id,
    r.cliente_id,
    r.funcion_id,
    p.nombre AS cliente,
    p.correo AS cliente_correo,
    p.telefono AS cliente_telefono,
    c.edad AS cliente_edad,
    pel.id AS pelicula_id,
    pel.titulo AS pelicula,
    gp.descripcion AS genero,
    pel.duracion_minutos,
    pel.precio_base,
    pel.edad_minima,
    s.id AS sala_id,
    s.nombre AS sala,
    s.capacidad AS sala_capacidad,
    ts.descripcion AS tipo_sala,
    ts.recargo AS recargo_sala,
    CONVERT(VARCHAR(10), f.fecha, 120) AS fecha,
    CONVERT(VARCHAR(8), f.hora, 108) AS hora,
    r.cantidad_boletos,
    er.descripcion AS estado,
    (pel.precio_base + ts.recargo) AS precio_unitario,
    (pel.precio_base + ts.recargo) * r.cantidad_boletos AS subtotal,
    CASE 
        WHEN r.cantidad_boletos >= 5 THEN ((pel.precio_base + ts.recargo) * r.cantidad_boletos) * 0.15
        WHEN r.cantidad_boletos >= 3 THEN ((pel.precio_base + ts.recargo) * r.cantidad_boletos) * 0.10
        ELSE 0.00
    END AS descuento,
    ((pel.precio_base + ts.recargo) * r.cantidad_boletos) - 
    CASE 
        WHEN r.cantidad_boletos >= 5 THEN ((pel.precio_base + ts.recargo) * r.cantidad_boletos) * 0.15
        WHEN r.cantidad_boletos >= 3 THEN ((pel.precio_base + ts.recargo) * r.cantidad_boletos) * 0.10
        ELSE 0.00
    END AS total_a_pagar,
    r.fecha_reserva
FROM dbo.reservas r
INNER JOIN dbo.clientes c ON r.cliente_id = c.id
INNER JOIN dbo.personas p ON c.id = p.id
INNER JOIN dbo.funciones f ON r.funcion_id = f.id
INNER JOIN dbo.peliculas pel ON f.pelicula_id = pel.id
INNER JOIN dbo.generos_pelicula gp ON pel.genero_id = gp.id
INNER JOIN dbo.salas s ON f.sala_id = s.id
INNER JOIN dbo.tipos_sala ts ON s.tipo_sala_id = ts.id
INNER JOIN dbo.estados_reserva er ON r.estado_id = er.id;
GO

-- Vista para consultar historial de facturas
CREATE OR ALTER VIEW dbo.vw_facturas_detalle AS
SELECT 
    f.id AS factura_id,
    f.numero,
    f.reserva_id,
    mp.nombre AS metodo_pago,
    f.pagada,
    f.fecha_emision
FROM dbo.facturas f
INNER JOIN dbo.metodos_pago mp ON f.metodo_pago_id = mp.id;
GO