package org.asciidoctor.gradle.model

import org.gradle.model.Managed

/**
 * Created by pledbrook on 03/02/2016.
 */
@Managed
abstract class PdfBinarySpecInternal implements AsciidocBinarySpecInternal, PdfBinarySpec {
    @Override
    String getBackend() { return "pdf" }
}
