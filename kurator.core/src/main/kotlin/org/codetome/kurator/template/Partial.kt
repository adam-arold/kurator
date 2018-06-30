package org.codetome.kurator.template

import kotlinx.html.TagConsumer
import org.codetome.kurator.behavior.Includable

abstract class Partial<T>(override val contentBuilder: TagConsumer<*>.(T) -> Unit)
    : Includable<T>
