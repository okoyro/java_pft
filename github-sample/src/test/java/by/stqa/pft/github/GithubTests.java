package by.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.RepoCommit;
import com.jcabi.github.RepoCommits;
import com.jcabi.github.RtGithub;
import com.sun.org.apache.xpath.internal.operations.String;
import org.testng.annotations.Test;

public class GithubTests {
  @Test
  public void testCommits() {
    Github github = new RtGithub("da143dc6dec7c642998fe3aff6b8969c80887b47");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("okoyro", "java_pft")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap<String,String>().build())) {
      System.out.println(commit);
    }
  }
}
