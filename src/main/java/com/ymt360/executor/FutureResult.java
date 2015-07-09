package com.ymt360.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


public class FutureResult extends FutureTask<ResultVo> {

	private String threadName;
	public FutureResult(Callable<com.ymt360.stress.ResultVo> callable) {
		super(callable);
		// TODO Auto-generated constructor stub
		this.threadName = ((HttpWorker)callable).getThreadName();
	}
	
	@Override
	protected void done() {
		if (isCancelled()) {
			System.out.printf("%s: Has been cancelled\n",threadName);
		} else {
			System.out.printf("%s: Has finished\n",threadName);
		}
	}

}
