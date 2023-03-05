package org.example;

import com.google.common.hash.Hashing;
import org.bitcoinj.core.Bech32;
import org.bouncycastle.jcajce.provider.digest.RIPEMD160;
import org.bouncycastle.util.encoders.Hex;

import java.io.ByteArrayOutputStream;

public class Main {
    // https://docs.cosmos.network/v0.47/architecture/adr-028-public-key-addresses#legacy-public-key-addresses-dont-change
    static byte[] compressedPubKeyToAddrBytes(final byte[] pubKey) {
        if(pubKey==null || pubKey.length!=33) {
            throw new IllegalArgumentException("compressed public key should be 33 bytes long");
        }
        // 256 bits = 32 bytes. address bytes should be 20 bytes
        byte[] sha256 = Hashing.sha256().hashBytes(pubKey).asBytes();
        // 160 bits = 20 bytes.
        RIPEMD160.Digest digest = new RIPEMD160.Digest();
        return  digest.digest(sha256);
    }

    private static byte[] convertBytesForBase32(final byte[] pubKey) {
        return convertBits(pubKey, 0, pubKey.length, 8, 5, true);
    }

    /** following code is copied https://github.com/sipa/bech32/pull/40/ */
    private static byte[] convertBits(
            final byte[] in,
            final int inStart,
            final int inLen,
            final int fromBits,
            final int toBits,
            final boolean pad) {
        int acc = 0;
        int bits = 0;
        ByteArrayOutputStream out = new ByteArrayOutputStream(64);
        final int maxv = (1 << toBits) - 1;
        final int max_acc = (1 << (fromBits + toBits - 1)) - 1;
        for (int i = 0; i < inLen; i++) {
            int value = in[i + inStart] & 0xff;
            if ((value >>> fromBits) != 0) {
                throw new RuntimeException(
                        String.format("Input value '%X' exceeds '%d' bit size", value, fromBits));
            }
            acc = ((acc << fromBits) | value) & max_acc;
            bits += fromBits;
            while (bits >= toBits) {
                bits -= toBits;
                out.write((acc >>> bits) & maxv);
            }
        }
        if (pad) {
            if (bits > 0) out.write((acc << (toBits - bits)) & maxv);
        } else if (bits >= fromBits || ((acc << (toBits - bits)) & maxv) != 0) {
            throw new RuntimeException("Could not convert bits, invalid padding");
        }
        return out.toByteArray();
    }

    private static String toBech32(final String hrp, final byte[] data) {
        byte[] base32Binary = convertBytesForBase32(data);
        return Bech32.encode(Bech32.Encoding.BECH32, hrp, base32Binary);
    }

    public static String hexPubKey2address(final String hexCompressedPubKey) {
        byte[] compressedPublicKey = Hex.decode(hexCompressedPubKey);
        byte[] addressBytes = compressedPubKeyToAddrBytes(compressedPublicKey);
        return toBech32("link", addressBytes);
    }
}