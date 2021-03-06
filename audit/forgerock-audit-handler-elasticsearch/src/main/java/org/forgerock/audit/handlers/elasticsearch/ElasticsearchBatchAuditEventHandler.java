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
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2016 ForgeRock AS.
 */
package org.forgerock.audit.handlers.elasticsearch;

import org.forgerock.json.JsonValue;
import org.forgerock.util.promise.Promise;

/**
 * Elasticsearch batch audit event handler.
 */
interface ElasticsearchBatchAuditEventHandler {

    /**
     * Adds an audit event to an Elasticsearch Bulk API payload.
     *
     * @param topic Event topic
     * @param event Event JSON payload
     * @param payload Elasticsearch Bulk API payload
     * @throws BatchException indicates failure to add-to-batch
     */
    void addToBatch(String topic, JsonValue event, StringBuilder payload) throws BatchException;

    /**
     * Publishes a <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-bulk.html">Bulk API</a>
     * payload to Elasticsearch.
     *
     * @param payload Elasticsearch Bulk API payload
     * @throws BatchException indicates (full or partial) failure to publish batch
     */
    Promise<Void, BatchException> publishBatch(String payload);
}
