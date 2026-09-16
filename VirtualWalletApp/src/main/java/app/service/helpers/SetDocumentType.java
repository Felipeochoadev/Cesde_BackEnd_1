package app.service.helpers;

import app.domain.DocumentTypeEnum;

public class SetDocumentType {
    public static String getDocumentType(int option) {
        option = 0;
        String value = "";
        switch (option) {
            case 1:
                value = DocumentTypeEnum.CEDULA.getValue();
                break;
            case 2:
                value = DocumentTypeEnum.PASSPORT.getValue();
                break;
            case 3:
                value = DocumentTypeEnum.CEDULA_EXTRANJERIA.getValue();
                break;
            case 4:
                value = DocumentTypeEnum.NIT.getValue();
                break;
            default:
                value = "opcion incorrecta";
            break;
        }

        return value;
    }
}
