package org.iesra

import java.io.File

fun procesarLog(
    fichero: File,
    from: String?,
    to: String?,
    level: String?,
    stats: Boolean,
    report: Boolean,
    outputFile: String?,
    stdout: Boolean,
    ignorarInvalidas: Boolean
) {
    val lineas = fichero.readLines()

    val entradasValidas = mutableListOf<EntradaLog>()
    val lineasInvalidas = mutableListOf<String>()

    for (linea in lineas) {
        val entrada = parsearLinea(linea)
        if (entrada != null) {
            entradasValidas.add(entrada)
        } else {
            lineasInvalidas.add(linea)
            if (!ignorarInvalidas) {
                System.err.println("Linea invalida: $linea")
            }
        }
    }

    val filtradas = filtrarEntradas(entradasValidas, from, to, level)

    val salida = if (stats && !report) {
        generarEstadisticas(lineas.size, entradasValidas.size, lineasInvalidas.size, filtradas, fichero.name, from, to, level)
    } else {
        generarInforme(lineas.size, entradasValidas.size, lineasInvalidas, filtradas, fichero.name, from, to, level, ignorarInvalidas)
    }

    if (stdout) {
        print(salida)
    }

    if (outputFile != null) {
        File(outputFile).writeText(salida)
        System.err.println("Informe guardado en: $outputFile")
    }
}