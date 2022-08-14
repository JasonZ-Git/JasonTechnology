## Install - Use bazelisk to install and manage bazel version
```BASH
npm install -g @bazel/bazelisk

```

## Bazel history
-- released in 2015 in Google, written in Java;
-- Support any programming language
-- cross platform

## baze command
```BASH
bazel help
bazel version # version
bazel build //path/to/package:target-name # build a target, it supports build multiple targets at a time
bazel query --notool_deps --noimplicit_deps "deps(//:ProjectRunner)" --output graph # dependency
bazel run target_name # run a target
bazel fetch target_name # fetch all dependencies
```
 ## bazel java rule
 java_binary - A rule to build a runable jar file with script - main class, standalone
    -- name - name of the rule, mandatory
    -- srcs - use maven2 structure for java files 
    -- resources - use maven2 structure for resource files
    -- deps - compile time dependency jars
    -- runtime_deps - other runtime dependency jars

    -- resource_strip_prefix
    -- data, args, classpath_resources, 
    -- compatible_with, create_executable, deploy_env, deploy_manifest_lines, 
    -- deprecation, distribs, env, exec_compatible_with, exec_properties, features, 
    -- javacopts, jvm_flags, launcher, licenses, main_class, output_licenses, plugins, 
    -- resource_jars, restricted_to , stamp, tags, target_compatible_with, 
    -- testonly, toolchains, use_launcher, visibility

## java_library rule - build a jar file
java_library

## java_import rule - Use precompile jar as source for java_library or java_binary
java_import 
    -- name
    -- jars
    -- deps

```bash 
java_import(
        name = "maven_model",
        jars = [
            "maven_model/maven-aether-provider-3.2.3.jar",
            "maven_model/maven-model-3.2.3.jar",
            "maven_model/maven-model-builder-3.2.3.jar",
        ],
    )
```
## java_test

## java_runtime