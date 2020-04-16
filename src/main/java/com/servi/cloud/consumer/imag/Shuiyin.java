package com.servi.cloud.consumer.imag;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * 给图像加水印
 */
public class Shuiyin {

    public static void main(String[] args) throws IOException {
        Thumbnails.of("extend/fp.jpg")
                .size(1280, 1024)
                .watermark(Positions.TOP_LEFT, ImageIO.read(newFile("extend/ycy.png")), 0.5f)
                .outputQuality(0.8f)
                .toFile("d:/fp.jpg");

    }

    private static File newFile(String path) {
        return new File(path);
    }
}
