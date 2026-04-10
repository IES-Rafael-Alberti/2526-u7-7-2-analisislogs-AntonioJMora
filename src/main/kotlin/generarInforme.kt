package org.iesra

fun generarEstadisticas(
    totalLineas: Int,
    lineasValidas: Int,
    lineasInvalidas: Int,
    entradas: List<EntradaLog>,
    nombreFichero: String,
    from: String?,
    to: String?,
    level: String?
): String {
    val sb = StringBuilder()
    sb.appendLine("ESTADISTICAS DE LOGS")
    sb.appendLine("---------")
    sb.appendLine("Fichero analizado: $nombreFichero")

    //Muestra solo en el rango que el usuario haya dado la fecha
    if (from != null || to != null) {
        sb.appendLine("Rango aplicado: ${from ?: "*"} -> ${to ?: "*"}")
    }

    val mensajesMostrados = level ?: "INFO, WARNING, ERROR"
    sb.appendLine("Mensajes incluidos: $mensajesMostrados")

    sb.appendLine()
    sb.appendLine("Resumen:")
    sb.appendLine("  - Lineas procesadas: $totalLineas")
    sb.appendLine("  - Lineas validas:    $lineasValidas")
    sb.appendLine("  - Lineas invalidas:  $lineasInvalidas")

    sb.appendLine()
    sb.appendLine("Conteo por mensaje:")
    sb.appendLine("  - INFO:    ${entradas.count { it.nivel == "INFO" }}")
    sb.appendLine("  - WARNING: ${entradas.count { it.nivel == "WARNING" }}")
    sb.appendLine("  - ERROR:   ${entradas.count { it.nivel == "ERROR" }}")

    sb.appendLine()
    sb.appendLine("Periodo detectado en los logs:")
    sb.appendLine("  - Primera entrada: ${entradas.firstOrNull()?.fecha ?: "N/A"}")
    sb.appendLine("  - Ultima entrada:  ${entradas.lastOrNull()?.fecha ?: "N/A"}")

    return sb.toString()
}

fun generarInforme(
    totalLineas: Int,
    lineasValidas: Int,
    lineasInvalidas: List<String>,
    entradas: List<EntradaLog>,
    nombreFichero: String,
    from: String?,
    to: String?,
    level: String?,
    ignorarInvalidas: Boolean
): String {
    val sb = StringBuilder()

    sb.appendLine("INFORME DE LOGS")
    sb.appendLine("===============")
    sb.appendLine("Fichero analizado: $nombreFichero")

    if (from != null || to != null) {
        sb.appendLine("Rango aplicado: ${from ?: "*"} -> ${to ?: "*"}")
    }

    val mensajesMostrados = level ?: "INFO, WARNING, ERROR"
    sb.appendLine("Mensajes incluidos: $mensajesMostrados")

    sb.appendLine()
    sb.appendLine("Resumen:")
    sb.appendLine("  - Lineas procesadas: $totalLineas")
    sb.appendLine("  - Lineas validas:    $lineasValidas")
    sb.appendLine("  - Lineas invalidas:  ${lineasInvalidas.size}")

    sb.appendLine()
    sb.appendLine("Conteo por nivel:")
    sb.appendLine("  - INFO:    ${entradas.count { it.nivel == "INFO" }}")
    sb.appendLine("  - WARNING: ${entradas.count { it.nivel == "WARNING" }}")
    sb.appendLine("  - ERROR:   ${entradas.count { it.nivel == "ERROR" }}")

    sb.appendLine()
    sb.appendLine("Periodo detectado en los logs:")
    sb.appendLine("  - Primera entrada: ${entradas.firstOrNull()?.fecha ?: "N/A"}")
    sb.appendLine("  - Ultima entrada:  ${entradas.lastOrNull()?.fecha ?: "N/A"}")

    if (entradas.isNotEmpty()) {
        sb.appendLine()
        sb.appendLine("Entradas (${entradas.size}):")
        sb.appendLine("-".repeat(70))
        for (entrada in entradas) {
            sb.appendLine(entrada.lineaOriginal)
        }
    }

    if (!ignorarInvalidas && lineasInvalidas.isNotEmpty()) {
        sb.appendLine()
        sb.appendLine("Lineas invalidas (${lineasInvalidas.size}):")
        sb.appendLine("-".repeat(70))
        for (linea in lineasInvalidas) {
            sb.appendLine("  [!] $linea")
        }
    }

    return sb.toString()
}