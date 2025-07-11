/*
 *  Copyright 2018 The WebRTC project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a BSD-style license
 *  that can be found in the LICENSE file in the root of the source
 *  tree. An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */

package org.webrtc;

import android.media.MediaCodecInfo;
import androidx.annotation.Nullable;

/** Factory for Android platform software VideoDecoders. */
public class JitsiPlatformVideoDecoderFactory extends MediaCodecVideoDecoderFactory {
    /**
     * Default allowed predicate.
     */
    private static final Predicate<MediaCodecInfo> defaultAllowedPredicate =
        codecInfo -> {
            // We only want to use the platform software codecs.
            return MediaCodecUtils.isSoftwareOnly(codecInfo);
        };

    /**
     * Creates a PlatformSoftwareVideoDecoderFactory that supports surface texture rendering.
     *
     * @param sharedContext The textures generated will be accessible from this context. May be null,
     *                      this disables texture support.
     */
    public JitsiPlatformVideoDecoderFactory(@Nullable EglBase.Context sharedContext) {
        super(sharedContext, defaultAllowedPredicate);
    }

    public JitsiPlatformVideoDecoderFactory(@Nullable EglBase.Context sharedContext, @Nullable Predicate<MediaCodecInfo> codecAllowedPredicate) {
        super(sharedContext, codecAllowedPredicate == null ? defaultAllowedPredicate : codecAllowedPredicate.and(defaultAllowedPredicate));
    }
}
