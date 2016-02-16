package org.asciidoctor.gradle.model

import org.gradle.model.Managed
import org.gradle.platform.base.ComponentSpec
import org.gradle.platform.base.SourceComponentSpec
import org.gradle.platform.base.VariantComponentSpec

/**
 * Created by pledbrook on 03/02/2016.
 */
@Managed
// 2.12
interface AsciidocDocumentSpec extends SourceComponentSpec, VariantComponentSpec {
// 2.11 interface AsciidocDocumentSpec extends ComponentSpec {
}
