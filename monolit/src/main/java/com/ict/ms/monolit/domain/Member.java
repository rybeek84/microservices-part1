package com.ict.ms.monolit.domain;

public interface Member {
    boolean canCreateTask();
    boolean canUpdateTask();
    boolean canCancelTask();
}
