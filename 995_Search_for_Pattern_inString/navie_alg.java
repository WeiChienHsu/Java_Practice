public static List<Integer> search(String p, String t) {
  int P = p.length();
  int T = t.length();
  List<Integer> res = new ArrayList<>();

  for(int i = 0; i <= P - T; i++) {
      int j;
      for(j = 0; j < T; j++) {
          if(p.charAt(i+j) == t.charAt(j)) {
              if(j == T - 1) res.add(i);
          }
      }
  }
  return res;
}
}
