It's a pleasure to read through your commit history. I've been running `git show solo-attempt~14` to step through your history one at a time and each commit is deliciously small. Truly driving tests like this gives us all confidence that you haven't missed testing a use case that you care about, and so long as the tests aren't destroyed we know we have that safety net. With tests like this, who cares about what the coverage metrics say.


bb5d692
You could technically have planned ahead and made those tests coincidentally work for the upcoming capitilization feature, but I think this is more honest / realistic. As long as you did your test changes before updating the code, or added the new test and made it just pass, and then updated the other tests, I think you're in good shape.

f684eac7fcf6d34113bd5376b73dbcf4bad15ca3
Gets a bit aggressive with how many tests it adds, but I can understand starting to loosen the one test constraint near the end of the kata....
