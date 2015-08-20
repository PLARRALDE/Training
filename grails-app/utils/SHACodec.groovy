/**
 * Created by plarralde on 17/8/15.
 */
import java.security.MessageDigest

class SHACodec {

    static encode = {target->
        MessageDigest md = MessageDigest.getInstance('SHA')
        md.update(target.getBytes('UTF-8'))
        return new String(md.digest()).encodeAsBase64()
    }


}
