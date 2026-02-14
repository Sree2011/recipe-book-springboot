/**
 * Root package for the Recipe Vault application.
 *
 * <p>This package serves as the entry point and central hub
 * for the application. It contains the main Spring Boot
 * application class and provides access to subpackages
 * that handle specific domains such as recipes, categories,
 * ingredients, configuration, and exception handling.
 *
 * <p>Subpackages include:
 * <ul>
 *   <li>{@link com.nandini.recipevault.recipe} - Recipe management</li>
 *   <li>{@link com.nandini.recipevault.category} - Recipe categorization</li>
 *   <li>{@link com.nandini.recipevault.ingredient} - Ingredient management</li>
 *   <li>{@link com.nandini.recipevault.exception} - Exception handling</li>
 * </ul>
 *
 * <p>Usage example:
 * <pre>
 * SpringApplication.run(RecipeVaultApplication.class, args);
 * </pre>
 */
package com.nandini.recipevault;