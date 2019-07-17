package net.consensys.liszt.transactionmanager;

import java.util.List;

public interface Services {

  /**
   * Add a transfer to the list of transfer to include, as soon as possible, in the rollup.
   *
   * @param rtx - the transfer
   * @return true if the transfer can be included, false otherwise.
   */
  boolean addTransaction(RTransfer rtx);

  /** @return the state of the transfer. */
  RTransferState getRTransferStatus(byte[] transferHas);

  /**
   * Select the transfer for the next batch.
   *
   * @param fatherRootHash - the starting point
   * @return the list of transfer selected.
   */
  List<RTransfer> selectRTransfersForNextBatch(byte[] fatherRootHash);

  /**
   * Mark the transfers as included.
   *
   * @param transfers - the list of transfers
   * @param rootHash - the root hash of the batch in which they are included.
   */
  void addToBatch(List<RTransfer> transfers, byte[] rootHash);

  /**
   * Start a new batch
   *
   * @param fatherRootHash - the father for this batch
   * @return todo
   */
  BatchState startNewBatch(byte[] fatherRootHash);

  /** @return a batch waiting to be proven. */
  Batch getBatchToProve();

  /**
   * Checks the proof is valid. If so the proof is kept and will be used to generate an ethereum tx.
   */
  void storeGeneratedProof(byte[] roothash, byte[] proof);

  /**
   * Generates an ethereum transaction for the corresponding Batch. The proof must have been
   * generated already.
   */
  byte[] generateTransaction(byte[] roothash);
}