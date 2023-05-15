package org.example;

public class CompareVersions {

    public static int compareVersions(String version1, String version2) {
        int comparisonResult = 0;

        String[] version1Splits = version1.split("\\.");
        String[] version2Splits = version2.split("\\.");
        int maxLengthOfVersionSplits = Math.max(version1Splits.length, version2Splits.length);

        for (int i = 0; i < maxLengthOfVersionSplits; i++){
            Integer v1 = i < version1Splits.length ? Integer.parseInt(version1Splits[i]) : 0;
            Integer v2 = i < version2Splits.length ? Integer.parseInt(version2Splits[i]) : 0;
            int compare = v1.compareTo(v2);
            if (compare != 0) {
                comparisonResult = compare;
                break;
            }
        }
        return comparisonResult;
    }

    public static void main(String[] args) {
        System.out.println(compareVersions("1.0.1", "1.1.2"));
        System.out.println(compareVersions("1.0.1", "1.10"));
        System.out.println(compareVersions("1.1.2", "1.0.1"));
        System.out.println(compareVersions("1.1.2", "1.2.0"));
        System.out.println(compareVersions("1.3.00", "1.3"));
    }
}
