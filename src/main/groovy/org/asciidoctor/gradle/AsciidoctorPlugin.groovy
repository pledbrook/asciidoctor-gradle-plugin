package org.asciidoctor.gradle

import org.asciidoctor.gradle.model.AsciidocBinarySpec
import org.asciidoctor.gradle.model.AsciidocBinarySpecInternal
import org.asciidoctor.gradle.model.AsciidocDocumentSpec
import org.asciidoctor.gradle.model.AsciidocSourceSet
import org.asciidoctor.gradle.model.HtmlBinarySpec
import org.asciidoctor.gradle.model.HtmlBinarySpecInternal
import org.asciidoctor.gradle.model.PdfBinarySpecInternal
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.model.Defaults
import org.gradle.model.Each
import org.gradle.model.Model
import org.gradle.model.ModelMap
import org.gradle.model.Mutate
import org.gradle.model.Path
import org.gradle.model.RuleSource
import org.gradle.model.Validate
import org.gradle.platform.base.BinaryTasks
import org.gradle.platform.base.BinaryType
import org.gradle.platform.base.ComponentBinaries
import org.gradle.platform.base.ComponentType
import org.gradle.platform.base.LanguageType
import org.gradle.platform.base.TypeBuilder

/**
 * Created by pledbrook on 25/11/2015.
 */
class AsciidoctorPlugin implements Plugin<Project> {
    @Override
    void apply(Project target) {
    }

    static class Rules extends RuleSource {
        /* 2.12 */
        @ComponentType
        void registerComponentType(TypeBuilder<AsciidocDocumentSpec> builder) {}

        /* 2.11
        @ComponentType
        void registerComponentType(ComponentTypeBuilder<AsciidocDocumentSpec> builder) {}
        */

        @BinaryType
        // 2.12
        void registerBinaryType(TypeBuilder<AsciidocBinarySpec> builder) {
        // 2.11 void registerBinaryType(BinaryTypeBuilder<AsciidocBinarySpec> builder) {
            builder.internalView(AsciidocBinarySpecInternal)
        }

//        @BinaryType
//        void registerHtmlBinaryType(TypeBuilder<HtmlBinarySpec> builder) {
//            builder.internalView(HtmlBinarySpecInternal)
//        }

//        @BinaryType
//        void registerPdfBinaryType(TypeBuilder<PdfBinarySpecInternal> builder) {
//            builder.internalView(PdfBinarySpecInternal)
//        }

        /* 2.12 */
        @LanguageType
        void registerSourceSetType(TypeBuilder<AsciidocSourceSet> builder) {
        }

        /* 2.11
        @LanguageType
        void registerSourceSetType(LanguageTypeBuilder<AsciidocSourceSet> builder) {
            builder.languageName = "Asciidoc"
        }
         */

        /** 2.12 */
        @Defaults
        void createMainSourceSet(@Each AsciidocDocumentSpec document) {
            document.sources.create("asciidoc", AsciidocSourceSet) { sourceSet ->
                sourceSet.source.srcDir("src/${document.name}/asciidoc")
            }
        }

//        @Defaults
//        void createMainSourceSet(ModelMap<AsciidocSourceSet> sourceSets) {
//            sourceSets.create("docs") { ss ->
//                ss.srcDir = "src/${ss.name}/asciidoc"
//                ss.includes = ["**/*.adoc"]
//            }
//        }
//
//        @Defaults
//        void createBackends(ModelMap<AsciidocBackend> backends) {
//            backends.create("html5") { backend ->
//                backend.outputDir = "html"
//            }
//
//            backends.create("docbook") { backend ->
//                backend.outputDir = "docbook"
//            }
//        }

        @ComponentBinaries
        void generateBinaries(
                ModelMap<AsciidocBinarySpecInternal> binaries,
                AsciidocDocumentSpec component,
                @Path("buildDir") File buildDir) {
            println "Running generateBinaries() rule"
            println "Binaries: ${binaries}"
            println "Component: ${component.name}"
            binaries.create("html", HtmlBinarySpecInternal) { binary ->
                binary.document = component
//                    outputDir = new File(buildDir, "${component.name}/${binary.name}")
            }

            binaries.create("pdf", PdfBinarySpecInternal) { binary ->
                binary.document = component
            }
        }

        @BinaryTasks
        void generateTasks(ModelMap<Task> tasks, AsciidocBinarySpecInternal binarySpec) {
            binarySpec.inputs.withType(AsciidocSourceSet) { ss ->
                def taskName = binarySpec.tasks.taskName("asciidoctor", binarySpec.document.name) +
                        "As${binarySpec.name.capitalize()}"
                tasks.create(taskName, AsciidoctorTask) { task ->
                    task.backend = binarySpec.backend
                }
            }
        }
    }
}
