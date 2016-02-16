package org.asciidoctor.gradle.model

import org.gradle.model.Managed

/**
 * Created by pledbrook on 03/02/2016.
 */
@Managed
abstract class HtmlBinarySpecInternal implements HtmlBinarySpec, AsciidocBinarySpecInternal {
    @Override
    String getBackend() { return "html5" }
}
