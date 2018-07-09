package org.codetome.kurator.extensions

import java.io.File

fun File.exists(fn: (File) -> Unit) {
    if (exists()) {
        fn(this)
    }
}
