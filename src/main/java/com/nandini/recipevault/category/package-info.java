/**
 * Provides classes and services for organizing recipes
 * into categories within the Recipe Vault application.
 *
 * <p>This package includes:
 * <ul>
 *   <li>Category entity and DTOs</li>
 *   <li>Repository interfaces for persistence</li>
 *   <li>Service layer for category management</li>
 *   <li>Controllers for exposing category APIs</li>
 * </ul>
 *
 * <p>Usage example:
 * <pre>
 * Category dessert = new Category("Desserts");
 * categoryService.save(dessert);
 * </pre>
 */
package com.nandini.recipevault.category;