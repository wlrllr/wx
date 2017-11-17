package com.wlrllr.sdk.util;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.content.AbstractContentBody;
import org.apache.http.util.Args;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by wlrllr on 2017/11/14.
 */
public class InputStreamBody extends AbstractContentBody {

    private final InputStream in;
    private final String filename;
    private long length;

    public InputStreamBody(final InputStream in, long length, final String filename, ContentType contentType) {
        super(contentType);
        if (in == null) {
            throw new IllegalArgumentException("Input stream may not be null");
        }
        this.in = in;
        this.filename = filename;
        this.length = length;
    }

    public InputStreamBody(final InputStream in, long length, final String filename) {
        this(in, length, filename, ContentType.create("application/octet-stream"));
    }

    public InputStreamBody(final InputStream in, long length) {
        this(in, length, "no_name", ContentType.create("application/octet-stream"));
    }

    public InputStream getInputStream() {
        return this.in;
    }

    public void writeTo(final OutputStream out) throws IOException {
        Args.notNull(out, "Output stream");
        try {
            byte[] tmp = new byte[4096];
            int l;
            while ((l = this.in.read(tmp)) != -1) {
                out.write(tmp, 0, l);
            }
            out.flush();
        } finally {
            this.in.close();
        }
    }

    public String getTransferEncoding() {
        return MIME.ENC_BINARY;
    }

    public String getCharset() {
        return null;
    }

    public long getContentLength() {
        return this.length;
    }

    public String getFilename() {
        return this.filename;
    }
}
