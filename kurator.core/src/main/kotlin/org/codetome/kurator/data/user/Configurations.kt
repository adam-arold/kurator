package org.codetome.kurator.data.user

import org.codetome.kurator.data.user.builder.ConfigurationBuilder
import org.codetome.kurator.data.domain.Configuration

/**
 * Builder function for creating [Configuration]s.
 */
fun configure(builder: ConfigurationBuilder.() -> Unit): Configuration {
    val cb = ConfigurationBuilder()
    builder.invoke(cb)
    return cb.build()
}
