/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions Copyrighted [year] [name of copyright owner]".
 *
 * Copyright 2011-2016 ForgeRock AS.
 */
package org.forgerock.json.crypto;

import org.forgerock.json.JsonValue;
import org.forgerock.json.JsonValueException;
import org.forgerock.json.JsonValueFunctions;
import org.forgerock.json.JsonValueTraverseFunction;
import org.forgerock.json.crypto.JsonCrypto;
import org.forgerock.json.crypto.JsonCryptoException;
import org.forgerock.json.crypto.JsonDecryptor;
import org.forgerock.util.Reject;

public class JsonDecryptFunction extends JsonValueTraverseFunction {

   private final JsonDecryptor decryptor;


   public JsonDecryptFunction(JsonDecryptor decryptor) {
      super(JsonValueFunctions.identity());
      this.decryptor = (JsonDecryptor)Reject.checkNotNull(decryptor);
   }

   protected Object traverseMap(JsonValue value) {
      if(JsonCrypto.isJsonCrypto(value)) {
         JsonCrypto crypto = new JsonCrypto(value);
         if(crypto.getType().equals(this.decryptor.getType())) {
            try {
               JsonValue jce = this.decryptor.decrypt(crypto.getValue());
               return this.apply(jce);
            } catch (JsonCryptoException var4) {
               throw new JsonValueException(value, var4);
            }
         }
      }

      return super.traverseMap(value);
   }
}
