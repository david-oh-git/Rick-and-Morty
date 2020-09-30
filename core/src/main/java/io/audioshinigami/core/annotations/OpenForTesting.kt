package io.audioshinigami.core.annotations

/**
 *  This annotation allows to open a class for mocking.
 */
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class OpenClass

/**
 *  Annotate class if you want it open in debug builds.
 */
@OpenClass
@Target(AnnotationTarget.CLASS)
annotation class OpenForTesting
