package org.codetome.kurator.data.raw

import com.vladsch.flexmark.ast.Document

/**
 * Contains the raw data which is loaded from an user-supplied markdown document.
 */
data class RawMarkdownDocument(
        /**
         * The document content **without** the front matter
         */
        val document: Document,
        /**
         * The unparsed frontmatter string
         */
        val frontMatterStr: String)
