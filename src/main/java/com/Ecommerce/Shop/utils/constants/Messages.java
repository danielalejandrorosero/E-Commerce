package com.Ecommerce.Shop.utils.constants;



public enum Messages {
    success("¡Operación completada con éxito!"),
    error("¡La operación ha fallado!"),

    // auth
    wrong_password("¡La contraseña es incorrecta!"),
    unauthorized("¡No autorizado!"),
    invalid_token("¡Token inválido!"),
    missing_token("¡No se proporcionó el token!"),
    email_not_found("¡No se pudo encontrar el correo electrónico!"),

    // user
    user_not_found("¡No se pudo encontrar al usuario!"),

    // category
    category_not_found("¡No se pudo encontrar la categoría!"),
    category_already_exists("¡La categoría ya existe!"),

    // product
    product_not_found("¡No se pudo encontrar el producto!"),
    product_already_exists("¡El producto ya existe!"),

    // order
    order_not_found("¡No se pudo encontrar el pedido!"),
    order_already_exists("¡El pedido ya existe!"),
    order_cant_update("¡El pedido ya se ha completado!"),
    not_blank("¡El campo debe estar lleno!");


    private final String text;

    Messages(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
