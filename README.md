# kraken-cljs

Example configuration of a non-trivial ClojureScript/Node.js interop.
I have in mind teams that want to use ClojureScript with existing Node.js libraries/ecosystem.

## Overview

I've been using [KrakenJS][kraken] with [BookshelfJS][bookshelf] to write a web app, and sorely missed the functional programming/macros I had when starting Clojure.
REST apis, database calls, data validations, model definitions... They're all just so repetitive!

[kraken]: http://krakenjs.com/

[bookshelf]: http://bookshelfjs.org/

I also sorely missed [`core.async`](https://github.com/clojure/core.async), which basically brings Go's channel abstraction to ClojureScript. 
While the repetitive Javascript issue could be fixed with better custom tooling (yay, fixing tech debt without working on features, exactly what we want...) or something like SweetJS, `core.async` isn't really replaceable in JS land.
Reasoning through async database calls is really annoying as each async pattern seems to have a different solution.
Even when you're using promises. Channels are a far more powerful abstraction.

Hence, this project. My goal is to have a source directory where both ClojureScript and Javascript code lives.
You can require Javascript from ClojureScript, and require ClojureScript from Javascript.

I also want to take advantage of some of Node.js's build ecosystem, in particular, Grunt, which I'm most familiar with.
You can, however use Gulp or whatever you're most familiar with in this setup.

## File Organization

The project is a mies template, with minor modifications for Node.js compatibility.
Then, I used the Yeoman Kraken generator to make a project, and put it in the `src/kraken_cljs` directory.
This way, you can run the `lein` and `script` commands in the project root, and `grunt` commands in the mixed js/cljs source root at `src/kraken_cljs`.

The two tricks I used are 

1. all npm dependencies go on the Node.js side (it has better support for js ecosystem obviously) in the js/cljs source root, so node_modules in the cljs project directory are symlinked into the project root so ClojureScript can use them.
2. all compiled cljs goes to the `out/` directory in the project root. Here, I've made a super simple `cljs_shim.js` file to load all your good bits of ClojureScript.

## Interop

Calling CLJS from JS:

    require.main.require('./cljs_shim'); # include ClojureScript and Google Closure. Do this once in your app entrypoint.

    goog.require('your.namespace');
    your.namespace.function();

Calling JS from CLJS:

    (def test (-> js/require .-main (.require './test.js'))) ; this can definitely be made prettier
    ((-> test .fun)) ; call function called fun

## Dev environment

You obviously get all your grunt stuff in the js/cljs source directory.

The following are commands to give you the nice lisp-y dev experience:

To get a REPL going:
To start an auto-building Node REPL 

    ./scripts/repl

To auto build when a cljs source file changes:

    ./script/watch
    # or
    lein cljsbuild auto server

Clean project:

    lein clean

## Injecting ClojureScript into your own project

You can do this in several rough steps:

1. Make a standalone mies project and get it to work with Node.js. 
  - The main thing to do is to set the compile target to `:nodejs` in the build and watch scripts (or in the `cljsbuild` config).
2. Verify that you can connect to the repl and get the code to auto-compile.
3. Place your existing JavaScript project into your `src/` directory.
4. Add the `cljs_shim.js` file and modify the paths in the file appropriately.
5. Symlink your `node_modules` from your JS directory back out into the ClojureScript project root directory.
  - Make sure you have the `source-map-support` module, as ClojureScript compiler depends on it.
6. Test everything out! Interop CLJS to JS, and JS to CLJS.
7. Be a happier Node.js coder!

## License

Copyright © Allan Jiang
