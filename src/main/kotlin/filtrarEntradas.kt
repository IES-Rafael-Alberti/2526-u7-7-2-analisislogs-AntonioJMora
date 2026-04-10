package org.iesra

val MENSAJES_VALIDOS = listOf("INFO", "WARNING", "ERROR")

fun filtrarEntradas(
    entradas: List<EntradaLog>,
    from: String?,
    to: String?,
    level: String?
): List<EntradaLog> {
    val mensajesFiltro: List<String>? = level?.split(",")?.map { it.trim().uppercase() }

    if (mensajesFiltro != null) {
        for (mensaje in mensajesFiltro) {
            if (mensaje !in MENSAJES_VALIDOS) {
                System.err.println("Mensaje desconocido: $mensaje. Los mensajes validos son: INFO, WARNING, ERROR")
            }
        }
    }
    val resultado = mutableListOf<EntradaLog>()

    for (entrada in entradas) {
        if (from?.let {entrada.fecha < it} == true) continue

        if (to?.let { entrada.fecha > it } == true) continue

        if (level != null && entrada.nivel !in level) continue

        resultado.add(entrada)
    }

    return resultado
}