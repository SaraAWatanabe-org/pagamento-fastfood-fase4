package com.challenge.fastfood.utils;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class EasyRandomUtils {
    // Cria uma única instância do EasyRandom com as configurações desejadas
    private static final EasyRandomParameters parameters = new EasyRandomParameters()
            .stringLengthRange(5, 15)        // Configura o tamanho das Strings
            .collectionSizeRange(1, 3)       // Configura o tamanho das coleções
            .seed(System.currentTimeMillis()); // Opcional: Seed para valores previsíveis

    private static final EasyRandom easyRandom = new EasyRandom(parameters);

    /**
     * Método utilitário para gerar um objeto mockado com valores aleatórios.
     *
     * @param clazz a classe do objeto a ser gerado
     * @param <T> o tipo da classe
     * @return uma instância da classe preenchida automaticamente
     */
    public static <T> T nextObject(Class<T> clazz) {
        return easyRandom.nextObject(clazz);
    }
}
