package org.iesra

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.UsageError
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file
import java.io.File

class OpcionesComando : CliktCommand(
    name = "logtool",
    help = "Procesa ficheros de log con formato: [YYYY-MM-DD HH:MM:SS] NIVEL Mensaje"
) {
    val inputComando : File by option("-i","--input", help = "Fichero de log de entrada. Obligatorio.")
        .file(mustExist = true, canBeDir = false)
        .required()

    val fromComando: String? by option("-f", "--from", help = "Fecha/hora inicial. Formato: YYYY-MM-DD HH:MM:SS")

    val toComando : String? by option("-t", "--to", help = "Fecha/hora final del filtro. Formato: \"YYYY-MM-DD HH:MM:SS\"")

    val levelComando : String? by option("-l", "--level", help = "Permite filtrar por uno o varios niveles. Ejemplo: INFO o ERROR,WARNING (deben estar separados por coma)")

    val statsComando : Boolean by option("-s", "--stats", help = "Muestra únicamente las estadísticas.").flag()

    val reportComando : Boolean by option("-r", "--report", help = "Genera un informe completo con detalle y estadísticas.").flag()

    val outputComando : String? by option("-o", "--output", help = "Guarda la salida en un fichero.")

    val stdoutComando : Boolean by option("-p", "--stdout", help = "Muestra la salida por consola.").flag()

    val ignoreInvalidComando : Boolean by option("--ignore-invalid", help = "Ignora líneas mal formadas y continúa el procesamiento.").flag()



    override fun run() {
        if (!stdoutComando && outputComando == null ) {
            throw UsageError("Debes indicar una salida: -p / --stdout  o  -o / --output <fichero>")
        }
        procesarLog(
            fichero = inputComando,
            from = fromComando,
            to = toComando,
            level = levelComando,
            stats = statsComando,
            report = reportComando,
            outputFile = outputComando,
            stdout = stdoutComando,
            ignorarInvalidas = ignoreInvalidComando
        )
    }
}