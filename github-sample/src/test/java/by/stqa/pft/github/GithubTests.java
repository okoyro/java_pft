package by.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.RepoCommit;
import com.jcabi.github.RepoCommits;
import com.jcabi.github.RtGithub;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {
  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("fa3b549c13c5da6cce5d806cb5b3aee2b6c7365e");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("okoyro", "java_pft")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String,String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
