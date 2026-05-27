package com.example.stealthvanish.diagnostic;

import java.util.List;

public record CompatibilityProbeReport(String result, int score, List<String> lines) {
}
