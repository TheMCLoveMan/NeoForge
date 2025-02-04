/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.neoforge.common.crafting;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * An ingredient type encapsulates the codecs to serialize and deserialize an ingredient.
 * <p>
 * {@code codec} allows ingredients that are known to be empty at deserialization time,
 * whereas {@code nonEmptyCodec} does not.
 */
public record IngredientType<T extends Ingredient>(MapCodec<T> codec, MapCodec<T> nonEmptyCodec) {
    /**
     * Constructor for ingredient types that have the same codec for empty and non-empty serialization.
     */
    public IngredientType(MapCodec<T> nonEmptyCodec) {
        this(nonEmptyCodec, nonEmptyCodec);
    }

    /**
     * Returns the right codec for this ingredient type based on {@code allowEmpty}.
     */
    public MapCodec<T> codec(boolean allowEmpty) {
        return allowEmpty ? codec : nonEmptyCodec;
    }
}
