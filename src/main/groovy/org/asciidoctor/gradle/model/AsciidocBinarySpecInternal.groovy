package org.asciidoctor.gradle.model

import org.gradle.model.Managed

/**
 * Created by pledbrook on 03/02/2016.
 */
@Managed
interface AsciidocBinarySpecInternal extends AsciidocBinarySpec {
    String getBackend()
    void setBackend(String backend)

    AsciidocDocumentSpec getDocument()
    void setDocument(AsciidocDocumentSpec doc)
}
