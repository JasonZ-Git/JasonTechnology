## Git Config - .gitconfig file

[user]
	name = 

	email = 

[alias]
	st = status -s

	lg = log --color --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit

	last = log --color --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit -10

	filelog = blame

[color]
	ui = true

[core]
	editor = vim

[diff]
	tool = meld

[difftool]
 prompt = false



