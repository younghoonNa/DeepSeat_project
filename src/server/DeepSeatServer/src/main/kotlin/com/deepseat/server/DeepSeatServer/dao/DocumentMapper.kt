package com.deepseat.server.DeepSeatServer.dao

import com.deepseat.server.DeepSeatServer.vo.Document
import org.springframework.stereotype.Repository

@Repository
interface DocumentMapper {

    fun insertDocument(document: Document)
    fun getDocuments(): List<Document>
    fun getDocumentsBySeatId(seatID: Int): List<Document>
    fun getDocumentById(docID: Int): Document?
    fun deleteDocument(docID: Int)
    fun updateDocument(document: Document)

}
