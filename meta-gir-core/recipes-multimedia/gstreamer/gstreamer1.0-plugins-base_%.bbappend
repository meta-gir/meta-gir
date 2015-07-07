FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

GIR_EXTRA_LIBS_PATH = "../sdp/.libs:../pbutils/.libs:../interfaces/.libs:../tag/.libs:../video/.libs:../audio/.libs"

inherit g-ir

include gstreamer1.0-plugins.inc

SRC_URI += "file://0001-Fix-build-error-in-riff.patch"
