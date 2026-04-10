package org.iesra

val REGEX_LINEA = Regex("""^\[(\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2})] (INFO|WARNING|ERROR) (.+)$""")

fun parsearLinea(linea: String): EntradaLog? {
    val resultado = REGEX_LINEA.matchEntire(linea.trim()) ?: return null

    val fecha = resultado.groupValues[1]
    val nivel = resultado.groupValues[2]
    val mensaje = resultado.groupValues[3]

    return EntradaLog(fecha, nivel, mensaje, linea)
}