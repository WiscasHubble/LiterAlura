package com.aluracursos.literalura.services;

public interface IConvertirDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
