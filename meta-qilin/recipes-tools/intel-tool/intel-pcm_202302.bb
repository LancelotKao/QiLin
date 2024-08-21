SUMMARY = "Intel® Performance Counter Monitor (Intel® PCM)"
HOMEPAGE = "https://github.com/intel/pcm/archive"
DESCRIPTION = "Intel® Performance Counter Monitor (Intel® PCM) is an application programming interface (API) and a set of tools based on the API to monitor performance and energy metrics of Intel® Core™, Xeon®, Atom™ and Xeon Phi™ processors. PCM works on Linux, Windows, Mac OS X, FreeBSD, DragonFlyBSD and ChromeOS operating systems."

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2d30532c8a8478266a67f85322d8478e"

BBDEBUG = "1"

SRC_URI = "gitsm://github.com/intel/pcm/;protocol=https;branch=master"

# Modify these as desired
PV = "git${SRCPV}"
SRCREV = "b0b18e9d4f254582ac7e406ecee885c25f254250"

S = "${WORKDIR}/git"

# NOTE: unable to map the following CMake package dependencies: simdjson
# NOTE: the following library dependencies are unknown, ignoring: IOKit
#       (this is based on recipes that have previously been built and packaged)
# NOTE: spec file indicates the license may be "BSD-3-Clause"
inherit cmake

INSANE_SKIP:${PN} += "already-stripped installed-vs-shipped"

