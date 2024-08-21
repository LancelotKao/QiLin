syntax on

set title

set nu
set ai

set tabstop=4
set softtabstop=4
set shiftwidth=4
set expandtab
"set noexpandtab
set hlsearch
set listchars=eol:¬,tab:>·,trail:~,extends:>,precedes:<,space:␣
"set list

highlight WhitespaceEOL ctermbg=red guibg=red
match WhitespaceEOL /\s\+$/

set t_Co=256
colorscheme koehler

au BufReadPost * if line("'\"") > 0|if line("'\"") <= line("$")|exe("norm '\"")|else|exe "norm $"|endif|endif

set cscopetag
set cscopeprg=gtags-cscope
cs add GTAGS

let GtagsCscope_Auto_Load = 1
let CtagsCscope_Auto_Map = 1
let GtagsCscope_Quite = 1

