package org.codetome.kurator.template

import org.codetome.kurator.behavior.Includable

abstract class Partial<T>(override val templateBuilder: TemplateBuilder<T>)
    : Includable<T>
