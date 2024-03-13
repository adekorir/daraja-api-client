module com.adekorir.oss.daraja.api {
    requires com.fasterxml.jackson.databind;
    requires java.logging;
    exports com.adekorir.oss.daraja.api;
    exports com.adekorir.oss.daraja.api.v1.auth;
    exports com.adekorir.oss.daraja.api.v1.c2b;
    exports com.adekorir.oss.daraja.api.v1.lnm;
}