package crypt;
import java.math.BigInteger;



public class RSAEncryption {
    
    public long p;
    public long q;
    public long d;

 
    public long N;
    public long e;

    // constructor
    public RSAEncryption(long p, long q){
        this.p = p;
        this.q = q;
        N = p*q;
        e = generatePublicKey();
        d = generatePrivateKey(e);
    }
    long pow(long b, long exp){
        long n = 1; // to return
        while(exp > 0){
            // std::cout << "e: " << e << std::endl;
            n *= b;
            --exp;
        }
        return n;
    }
    
    boolean isCoprime(long a, long b)
    {
        if (a == 0) {
            if (b > 1)
                return false;
            else
                return true;
         }
        return isCoprime(b % a, a);
    }

    public BigInteger encrypt(long m, long e){
        BigInteger message = new BigInteger( Long.toString(m) );
        BigInteger productPTimesQ = new BigInteger(Long.toString(N));
        return message.pow( (int) e).mod( productPTimesQ );
    }

    public BigInteger decrypt(BigInteger message){
        BigInteger productPTimesQ = new BigInteger(Long.toString(N));
        return message.pow( (int) d).mod( productPTimesQ );
    }

    long generatePublicKey(){
        long i = 3;
        while(!isCoprime(i, (p-1)*(q-1) ))  ++i;
        return i;
    }

    long generatePrivateKey(long e){
        long i = 1;
        while(  ((p-1)*(q-1)*i + 1) % e != 0 ) ++i;
        d = ((p-1)*(q-1)*i + 1) / e;
        //std::cout << "private key d: " << d << std::endl;
        //std::cout << "public key e: " << e << std::endl;
        // std::cout << "e*d % N: " << e*d % N << std::endl;
        return d;
    }

    
}
