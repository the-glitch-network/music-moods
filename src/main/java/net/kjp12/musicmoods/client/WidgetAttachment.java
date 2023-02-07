/* Copyright 2023 KJP12
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package net.kjp12.musicmoods.client;// Created 2023-17-01T20:34:47

import net.kjp12.musicmoods.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.sounds.SoundSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KJP12
 * @since 0.1.0
 **/
public final class WidgetAttachment {
	public static final Map<OptionInstance<?>, WidgetFactory> binding = new HashMap<>();

	public static void add(OptionInstance<?> instance, WidgetFactory factory) {
		binding.put(instance, factory);
	}

	public static void init(final Minecraft minecraft) {
		WidgetAttachment.add(minecraft.options.getSoundSourceOptionInstance(SoundSource.MUSIC), (widget, first) -> {
			final int x = deriveX(widget, first, Constants.smallButtonWidth, Constants.smallButtonPlacementOffset);
			final int y = widget.getY();

			return new ImageButton(x, y, Constants.smallButtonWidth, Constants.smallButtonWidth, 0, 0,
					Constants.smallButtonWidth, Constants.moodsResource, Constants.atlasSize, Constants.atlasSize,
					button -> minecraft.setScreen(new ConfigurationScreen(minecraft.screen)));
		});
	}

	public static int deriveX(final AbstractWidget reference, final boolean before, final int width, final int offset) {
		if (before) {
			return reference.getX() - width - offset;
		} else {
			return reference.getX() + reference.getWidth() + offset;
		}
	}

	@FunctionalInterface
	public interface WidgetFactory {
		AbstractWidget createWidget(AbstractWidget widget, boolean first);
	}
}