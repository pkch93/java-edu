package edu.pkch.extend;

public interface Walker {
    default int walk() { return 1; }
}
