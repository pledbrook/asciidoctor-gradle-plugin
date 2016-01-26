package org.asciidoctor.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.model.Defaults
import org.gradle.model.Model
import org.gradle.model.ModelMap
import org.gradle.model.Mutate
import org.gradle.model.RuleSource
import org.gradle.model.Validate

/**
 * Created by pledbrook on 25/11/2015.
 */
class AsciidoctorPlugin implements Plugin<Project> {
    @Override
    void apply(Project target) {
    }

    static class Rules extends RuleSource {
        @Model
        void sourceSets(ModelMap<AdocSourceSet> sourceSets) {}

        @Model
        void formats(ModelMap<AdocBackend> backends) {}

        @Defaults
        void createMainSourceSet(ModelMap<AdocSourceSet> sourceSets) {
            sourceSets.create("docs") { ss ->
                ss.srcDir = "src/${ss.name}/asciidoc"
                ss.includes = ["**/*.adoc"]
            }
        }

        @Defaults
        void createBackends(ModelMap<AdocBackend> backends) {
            backends.create("html5") { backend ->
                backend.outputDir = "html"
            }

            backends.create("docbook") { backend ->
                backend.outputDir = "docbook"
            }
        }

        @Mutate
        void createTasks(ModelMap<Task> tasks, ModelMap<AdocSourceSet> sourceSets, ModelMap<AdocBackend> backends) {
            if (sourceSets.size() > 0 && backends.size() > 0) {
                tasks.create("generateAllDocs")
            }

            sourceSets.values().each { ss ->
                backends.values().each { backend ->
                    def taskName = "generate${ss.name.capitalize()}To${backend.name.capitalize()}"
                    tasks.create(taskName) { task ->
                        task.doLast {
                            println "Generating Asciidoc for ${ss.name} source set as ${backend.name}"
                        }
                    }

                    tasks.get("generateAllDocs").dependsOn(tasks.get(taskName))
                }
            }
        }

        @Mutate
        void notCreatingTasks(ModelMap<Task> tasks) {
            println ">>> Not creating a task"
        }

        @Validate
        void hasSourceFiles(ModelMap<AdocSourceSet> sourceSets) {
            sourceSets.afterEach {
                assert it.srcDir != "src/not/funny"
            }
        }
    }
}
