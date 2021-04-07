package com.yvonne.zoom.spring.service;

/**
 * @author Yvonne Wang
 */
public interface IEmpTransferService {

    boolean transfer(int from, int to, int money);
}
