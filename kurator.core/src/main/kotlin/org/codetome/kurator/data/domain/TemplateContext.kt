package org.codetome.kurator.data.domain

/**
 * Context data which is applicable for a single [Document].
 */
data class TemplateContext(val page: Document,
                           val site: Site)
