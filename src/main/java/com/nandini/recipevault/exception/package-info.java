/**
 * Provides custom exception classes and handlers
 * for the Recipe Vault application.
 *
 * <p>This package centralizes error handling logic,
 * ensuring consistent responses across the API.
 * It includes:
 * <ul>
 *   <li>Custom exception types for domain-specific errors</li>
 *   <li>Global exception handlers for REST controllers</li>
 *   <li>Utility classes for standardized error messages</li>
 * </ul>
 *
 * <p>Usage example:
 * <pre>
 * if (recipeNotFound) {
 *     throw new RecipeNotFoundException("Recipe not found");
 * }
 * </pre>
 */
package com.nandini.recipevault.exception;